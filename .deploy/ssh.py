import paramiko
import os
import json

SSH_PASSWORD = os.environ['SSH_PASSWORD']
SSH_IP = os.environ['SSH_IP']
BACKEND_HEALTH_CHECK_URL = os.environ['BACKEND_HEALTH_CHECK_URL']

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

ssh.connect(SSH_IP, 22, 'root', SSH_PASSWORD)

print(f'Connect to {SSH_IP} OK')

# upload Toeic.war
ftp = ssh.open_sftp()
ftp.put('./target/Backend-Toeic-App-0.0.1-SNAPSHOT.war', '/root/BackendToeicAppDeploy/Toeic.war')
ftp.close()

print(f'Upload Toeic.war ok')

(_, stdout, stderr) = ssh.exec_command('cd BackendToeicAppDeploy && docker-compose down && docker-compose up -d')
exit_status = stdout.channel.recv_exit_status()

assert exit_status == 0

print('\n'.join(stdout.readlines()))

ssh.close()

print(f'Close connection {SSH_IP} OK')

print('Begin health check')

import requests
import time

counter = 0
while True:
    try:
        counter += 1
        resp = requests.get(BACKEND_HEALTH_CHECK_URL, verify=False, timeout=2)
        if resp.status_code == 200:
            break
    except requests.exceptions.Timeout:
        print(f'Waiting .... [{counter}]')
    except Exception as e:
        print(e)
        time.sleep(1)

print("OK")

