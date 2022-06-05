# Start the Kafka container and keep th terminal open 

#powershell 
docker-compose -f docker-compose.yml up -d
Start-Sleep -seconds 3
powershell -noexit  docker exec -it kafka /bin/sh -c "/opt/bitnami/kafka/config/custom_scripts/start_broker.sh"
#Read-Host -Prompt "Press any key to continue"
#powershell -noexit docker exec -it kafka /bin/sh
# Read-Host -Prompt "Press any key to continue"
# powershell -noexit cd ./opt/bitnami/kafka