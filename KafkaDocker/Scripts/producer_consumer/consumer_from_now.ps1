# Starts a simple consumer on port 9092
#
# Number of folders to go up
$dist = 2;
# Jump to the correct directory
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
#
Write-Output "Press ctrl+z to exit"
docker exec -it kafka /bin/sh -c "kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic topic1"
Read-Host -Prompt "Press Enter to continue"