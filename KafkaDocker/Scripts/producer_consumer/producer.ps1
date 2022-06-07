# Starts a simple producer on port 9092
#
# Number of folders to go up
$dist = 2;
# Loop to jump up a directory eevry time it loops
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
#
Write-Output "Press ctrl+z to exit"
docker exec -it kafka /bin/sh -c "kafka-console-producer.sh --broker-list kafka:9092 --topic topic1"
Read-Host -Prompt "Press Enter to continue"