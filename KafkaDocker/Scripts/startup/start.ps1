# Start the Kafka container and keep the terminal open 
#
# Number of folders to go up
$dist = 2;
# Loop to jump up a directory eevry time it loops
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
# Leaves the terminal open
powershell -noexit docker-compose -f docker-compose.yml up -d
#
# docker-compose -f docker-compose.yml up -d
# Creates a prompt in order to keep the terminal closed for input, but still open
# Read-Host -Prompt "Press Enter to continue"