package com.hcmute.backendtoeicapp.services.toeicbackup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcmute.backendtoeicapp.AppConfiguration;
import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.entities.*;
import com.hcmute.backendtoeicapp.repositories.*;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicBackupService;
import com.hcmute.backendtoeicapp.services.toeicbackup.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ToeicBackupServiceImpl implements ToeicBackupService {
    private static final Gson gsonInstance = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Autowired
    private ToeicFullTestRepository toeicFullTestRepository;

    @Autowired
    private ToeicPartRepository toeicPartRepository;

    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicQuestionRepository toeicQuestionRepository;

    @Autowired
    private ToeicAnswerChoiceRepository toeicAnswerChoiceRepository;

    @Autowired
    private ToeicItemContentRepository toeicItemContentRepository;

    @Autowired
    private ToeicStorageRepository toeicStorageRepository;

    @Autowired
    private AppConfiguration appConfiguration;

    @Override
    public byte[] backupToeicTest(Integer id) {
        return new byte[0];
    }

    @Override
    public BaseResponse restoreToeicTest(MultipartFile file) throws IOException {
        String fileZipName = file.getOriginalFilename();
        String fileExtension = ".zip";
        if (!getFileExtension(fileZipName).equals(fileExtension)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("File upload phải là file zip");
            return response;
        }

        InputStream inputStream = file.getInputStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ToeicFullTestBackupModel toeicFullTestBackupModel = null;
        Map<String, byte[]> fileMapping = new HashMap<>();

        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (zipEntry.isDirectory())
                continue;

            final String fullName = zipEntry.getName();
            if (fullName.equals("config.json")) {
                final String json = new String(zipInputStream.readAllBytes(), StandardCharsets.UTF_8);
                toeicFullTestBackupModel = gsonInstance.fromJson(json, ToeicFullTestBackupModel.class);
            }
            else {
                fileMapping.put(fullName, zipInputStream.readAllBytes());
            }
        }

        if (toeicFullTestBackupModel == null) {
            throw new RuntimeException("Không tìm thấy config.json");
        }

        // Check exist resource
//        for (ToeicPartBackupModel part : toeicFullTestBackupModel.getParts()) {
//            for (ToeicQuestionGroupBackupModel group : part.getGroups()) {
//                for (ToeicItemContentBackupModel trans : group.getTranscript()) {
//                    System.out.println("TRANS: " + trans.getType());
//                }
//                for (ToeicItemContentBackupModel cont : group.getQuestionContents()) {
//                    System.out.println("CONTS: " + cont.getType());
//                    if (cont.getType().equals("AUDIO") || cont.getType().equals("IMAGE")) {
//                        final boolean existed = fileMapping.containsKey(cont.getContent());
//                        if (!existed) {
//                            throw new RuntimeException("sss");
//                        }
//                    }
//                }
//            }
//        }
        int counter = 1;
        String newSlug = toeicFullTestBackupModel.getSlug();

        while (toeicFullTestRepository.getToeicFullTestBySlug(newSlug) != null) {
            newSlug = toeicFullTestBackupModel.getSlug() + "-" + counter;
            counter++;
        }

        ToeicFullTestEntity toeicFullTestEntity = new ToeicFullTestEntity();
        toeicFullTestEntity.setFullName(toeicFullTestBackupModel.getFullName());
        toeicFullTestEntity.setSlug(newSlug);
        toeicFullTestEntity.setCheckSum(UUID.randomUUID() + "");
        toeicFullTestRepository.save(toeicFullTestEntity);

        for (ToeicPartBackupModel part : toeicFullTestBackupModel.getParts()) {
            ToeicPartEntity partEntity = new ToeicPartEntity();
            partEntity.setPartNumber(part.getPartNumber());
            partEntity.setToeicFullTestEntity(toeicFullTestEntity);
            toeicPartRepository.save(partEntity);

            for (ToeicQuestionGroupBackupModel group : part.getGroups()) {
                ToeicQuestionGroupEntity questionGroupEntity = new ToeicQuestionGroupEntity();
                questionGroupEntity.setToeicPartEntity(partEntity);
                toeicQuestionGroupRepository.save(questionGroupEntity);

                for (ToeicQuestionBackupModel question : group.getQuestions()) {
                    ToeicQuestionEntity questionEntity = new ToeicQuestionEntity();
                    questionEntity.setQuestionNumber(question.getQuestionNumber());
                    questionEntity.setToeicQuestionGroupEntity(questionGroupEntity);
                    questionEntity.setCorrectAnswer(question.getCorrectAnswer());
                    questionEntity.setContent(question.getQuestion());
                    toeicQuestionRepository.save(questionEntity);

                    for (ToeicAnswerChoiceBackupModel choice : question.getChoices()) {
                        ToeicAnswerChoiceEntity answerChoiceEntity = new ToeicAnswerChoiceEntity();
                        answerChoiceEntity.setLabel(choice.getLabel());
                        answerChoiceEntity.setContent(choice.getContent());
                        answerChoiceEntity.setExplain(choice.getExplain());
                        answerChoiceEntity.setToeicQuestionEntity(questionEntity);
                        toeicAnswerChoiceRepository.save(answerChoiceEntity);
                    }
                }

                if (group.getQuestionContents() != null) {
                    for (ToeicItemContentBackupModel questionContent : group.getQuestionContents()) {
                        ToeicItemContentEntity toeicItemContentEntity = new ToeicItemContentEntity();
                        toeicItemContentEntity.setContentType(questionContent.getType());
                        toeicItemContentEntity.setContent(questionContent.getContent());
                        toeicItemContentEntity.setToeicQuestionGroupEntityQuestionContent(questionGroupEntity);
                        toeicItemContentRepository.save(toeicItemContentEntity);

                        if (questionContent.getType().equals("AUDIO") || questionContent.getType().equals("IMAGE")) {
                            if (!fileMapping.containsKey(questionContent.getContent())) {
                                throw new RuntimeException("File not found " + questionContent.getContent());
                            }

                            String fileName = randomFileName(questionContent.getContent());
                            Path root = Paths.get(this.appConfiguration.getToeicStoreDirectory());
                            Files.copy(new ByteArrayInputStream(fileMapping.get(questionContent.getContent())), root.resolve(fileName));
                            ToeicStorageEntity toeicStorageEntity = new ToeicStorageEntity();
                            toeicStorageEntity.setFileName(fileName);
                            this.toeicStorageRepository.save(toeicStorageEntity);

                            // Audio + Image thì không cần cái content làm chi
                            toeicItemContentEntity.setContent(null);
                            toeicItemContentEntity.setToeicStorageEntity(toeicStorageEntity);
                            toeicItemContentRepository.save(toeicItemContentEntity);
                        }
                    }
                }

                if (group.getTranscript() != null) {
                    for (ToeicItemContentBackupModel transcript : group.getTranscript()) {
                        ToeicItemContentEntity toeicItemContentEntity = new ToeicItemContentEntity();
                        toeicItemContentEntity.setContentType(transcript.getType());
                        toeicItemContentEntity.setContent(transcript.getContent());
                        toeicItemContentEntity.setToeicQuestionGroupEntityTranscript(questionGroupEntity);
                        toeicItemContentRepository.save(toeicItemContentEntity);
                    }
                }

            }
        }

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("ok");
        return response;
    }

    private static String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    private static String randomFileName(String fileName) {
        return UUID.randomUUID() + getFileExtension(fileName);
    }
}
