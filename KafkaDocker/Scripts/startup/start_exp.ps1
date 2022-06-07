# Starts the Kafka container, goes through the setup process described in the README and keeps the terminal open
# This is a script intended for experimentation.
#
# Number of folders to go up
$dist = 2;
# Loop to jump up a directory eevry time it loops
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
#
docker-compose -f docker-compose.yml up -d
Start-Sleep -seconds 3
powershell -noexit  docker exec -it kafka /bin/sh -c "/opt/bitnami/kafka/config/custom_scripts/start_broker.sh"
#Read-Host -Prompt "Press any key to continue"
#powershell -noexit docker exec -it kafka /bin/sh
# Read-Host -Prompt "Press any key to continue"
# powershell -noexit cd ./opt/bitnami/kafka