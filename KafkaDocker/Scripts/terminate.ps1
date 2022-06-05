# Terminate the Kafka Docker container
#
# Number of folders to go up
$dist = 1;
# Jump to the correct directory
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
#
powershell docker-compose -f docker-compose.yml down
Read-Host -Prompt "Press Enter to continue"