package com.hcmute.backendtoeicapp.services;

import com.hcmute.backendtoeicapp.base.BaseResponse;
import com.hcmute.backendtoeicapp.base.ErrorResponse;
import com.hcmute.backendtoeicapp.base.SuccessfulResponse;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateNewItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.CreateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.ToeicItemContentResponse;
import com.hcmute.backendtoeicapp.dto.toeicItemContent.UpdateToeicItemContentRequest;
import com.hcmute.backendtoeicapp.entities.ToeicItemContentEntity;
import com.hcmute.backendtoeicapp.entities.ToeicStorageEntity;
import com.hcmute.backendtoeicapp.repositories.ToeicItemContentRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicQuestionGroupRepository;
import com.hcmute.backendtoeicapp.repositories.ToeicStorageRepository;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicItemContentService;
import com.hcmute.backendtoeicapp.services.interfaces.ToeicStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToeicItemContentServiceImpl implements ToeicItemContentService {
    @Autowired
    private ToeicItemContentRepository toeicItemContentRepository;

    @Autowired
    private ToeicQuestionGroupRepository toeicQuestionGroupRepository;

    @Autowired
    private ToeicStorageRepository toeicStorageRepository;

    @Autowired
    private ToeicStorageService toeicStorageService;

    private static List<String> getContentTypes() {
        List<String> contentTypes = new ArrayList<>();
        contentTypes.add("HTML");
        contentTypes.add("AUDIO");
        contentTypes.add("PLAIN");
        contentTypes.add("IMAGE");
        return contentTypes;
    }
    @Override
    public BaseResponse getAllToeicItemContents() {
        List<ToeicItemContentEntity> toeicItemContentEntityList = this.toeicItemContentRepository.findAll();
        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(toeicItemContentEntityList);
        response.setMessage("Lấy dữ liệu thành công");
        return response;
    }

    @Override
    public BaseResponse getToeicItemContentById(Integer id) {
        if (!this.toeicItemContentRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic item content với id = " + id);
            return response;
        }
        ToeicItemContentEntity toeicItemContentEntity = this.toeicItemContentRepository.getById(id);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setData(toeicItemContentEntity);
        response.setMessage("Lấy dữ liệu thành công");
        return response;
    }

    @Override
    public BaseResponse getQuestionItemContentsByGroupId(Integer groupId) {
        if (!this.toeicQuestionGroupRepository.existsById(groupId)) {
            throw new RuntimeException("Không tìm thấy group nào với id = " + groupId);
        }

        List<ToeicItemContentEntity> result = this.toeicItemContentRepository.getListQuestionContentByQuestionGroupId(groupId);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(result);

        return response;
    }

    @Override
    public BaseResponse getTranscriptContentsByGroupId(Integer groupId) {
        if (!this.toeicQuestionGroupRepository.existsById(groupId)) {
            throw new RuntimeException("Không tìm thấy group nào với id = " + groupId);
        }

        List<ToeicItemContentEntity> result = this.toeicItemContentRepository.getListTranscriptByQuestionGroupId(groupId);

        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(result);

        return response;
    }

    @Override
    public BaseResponse createToeicItemContent(CreateToeicItemContentRequest request) throws Exception {
        List<String> contentTypes = getContentTypes();
        if (!contentTypes.contains(request.getContentType())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Chỉ nhận các giá trị gồm: HTML, PLAIN, IMAGE, AUDIO");
            return response;
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionContentId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question content với id = " + request.getQuestionContentId());
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionTranscriptId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question transcript với id = " + request.getQuestionTranscriptId());
        }
        if (!this.toeicStorageRepository.existsById(request.getStorageId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic storage id = " + request.getStorageId());
            return response;
        }
        ToeicItemContentEntity toeicItemContentEntity = new ToeicItemContentEntity();
        toeicItemContentEntity.setContentType(request.getContentType());
        toeicItemContentEntity.setContent(request.getContent());
        ToeicStorageEntity toeicStorageEntity = this.toeicStorageRepository.getById(request.getStorageId());
        if (request.getContentType().equals("HTML") || request.getContentType().equals("PLAIN")) {
            toeicStorageEntity = null;
        }
        toeicItemContentEntity.setToeicStorageEntity(toeicStorageEntity);

//        if ((this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) == null
//                && this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()) != null) ||
//        this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) != null
//                && this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()) == null)
//                {}
        if (this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) == null &&
        this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId())==null)
            throw new Exception("error");

        else {
            toeicItemContentEntity.setToeicQuestionGroupEntityQuestionContent(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()));
            toeicItemContentEntity.setToeicQuestionGroupEntityTranscript(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()));
        }
        this.toeicItemContentRepository.save(toeicItemContentEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(toeicItemContentEntity);
        return response;
    }

    @Override
    public BaseResponse updateToeicItemContent(UpdateToeicItemContentRequest request) throws Exception {
        List<String> contentTypes = getContentTypes();
        if (!contentTypes.contains(request.getContentType())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Chỉ nhận các giá trị gồm: HTML, PLAIN, IMAGE, AUDIO");
            return response;
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionContentId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question content với id = " + request.getQuestionContentId());
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionTranscriptId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question transcript với id = " + request.getQuestionTranscriptId());
        }
        if (!this.toeicStorageRepository.existsById(request.getStorageId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic storage id = " + request.getStorageId());
            return response;
        }
        ToeicItemContentEntity toeicItemContentEntity = this.toeicItemContentRepository.getById(request.getId());
        toeicItemContentEntity.setContentType(request.getContentType());
        toeicItemContentEntity.setContent(request.getContent());
        ToeicStorageEntity toeicStorageEntity = this.toeicStorageRepository.getById(request.getStorageId());
        if (request.getContentType().equals("HTML") || request.getContentType().equals("PLAIN")) {
            toeicStorageEntity = null;
        }
        toeicItemContentEntity.setToeicStorageEntity(toeicStorageEntity);
//        if ((this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) == null
//                && this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()) != null) ||
//                this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) != null
//                        && this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()) == null) {
//            toeicItemContentEntity.setToeicQuestionGroupEntityQuestionContent(
//                    this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()));
//            toeicItemContentEntity.setToeicQuestionGroupEntityTranscript(
//                    this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()));
//        }
        if (this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) == null &&
                this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId())==null)
            throw new Exception("error");

        else {
            toeicItemContentEntity.setToeicQuestionGroupEntityQuestionContent(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()));
            toeicItemContentEntity.setToeicQuestionGroupEntityTranscript(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()));
        }
        this.toeicItemContentRepository.save(toeicItemContentEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Cập nhật dữ liệu thành công");
        response.setData(toeicItemContentEntity);
        return response;
    }
    @Override
    public BaseResponse deleteToeicItemContent(Integer id) {
        if (!this.toeicItemContentRepository.existsById(id)) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại toeic item content với id = " + id);
            return response;
        }
        ToeicItemContentEntity toeicItemContentEntity = this.toeicItemContentRepository.getById(id);
        this.toeicItemContentRepository.delete(toeicItemContentEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Xóa dữ liệu thành công");
        return response;
    }

    @Override
    public BaseResponse createNewItemContent(CreateNewItemContentRequest request) throws Exception {
        List<String> contentTypes = getContentTypes();
        if (!contentTypes.contains(request.getContentType())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Chỉ nhận các giá trị gồm: HTML, PLAIN, IMAGE, AUDIO");
            return response;
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionContentId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question content với id = " + request.getQuestionContentId());
        }
        if (!this.toeicQuestionGroupRepository.existsById(request.getQuestionTranscriptId())) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("Không tồn tại question transcript với id = " + request.getQuestionTranscriptId());
        }
        ToeicItemContentEntity toeicItemContentEntity = new ToeicItemContentEntity();
        toeicItemContentEntity.setContentType(request.getContentType());
        if (toeicItemContentEntity.getContentType().equals("HTML") ||
                toeicItemContentEntity.getContentType().equals("PLAIN")) {
            toeicItemContentEntity.setContent(request.getStringContent());
        }
        else toeicItemContentEntity.setContent(null);
        ToeicStorageEntity toeicStorageEntity = null;
        if (request.getContent() != null) {
            try {
                toeicStorageEntity = this.toeicStorageService.saveByteArrayAndReturnEntity(
                        request.getContent().getOriginalFilename(),
                        request.getContent().getBytes()
                );
            } catch (IOException e) {
                throw new RuntimeException("Cannot read or save audio file");
            }
        }
        if (this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()) == null &&
                this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId())==null)
            throw new Exception("error");
        else {
            toeicItemContentEntity.setToeicQuestionGroupEntityQuestionContent(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionContentId()));
            toeicItemContentEntity.setToeicQuestionGroupEntityTranscript(
                    this.toeicQuestionGroupRepository.getById(request.getQuestionTranscriptId()));
        }
        toeicItemContentEntity.setToeicStorageEntity(toeicStorageEntity);
        this.toeicItemContentRepository.save(toeicItemContentEntity);
        SuccessfulResponse response = new SuccessfulResponse();
        response.setMessage("Tạo dữ liệu thành công");
        response.setData(new ToeicItemContentResponse(toeicItemContentEntity));
        return response;
    }
}
