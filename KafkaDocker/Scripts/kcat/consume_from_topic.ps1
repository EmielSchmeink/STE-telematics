# A kcat consumer BROKEN
#
# Number of folders to go up
$dist = 2;
# Loop to jump up a directory eevry time it loops
for (($i = 0); $i -lt $dist; $i++)
{
    Set-Location -Path ..
}
# Magic
powershell -noexit docker run --tty --network kafkadocker_back-tier confluentinc/cp-kafkacat kafkacat -b kafka:9092 -C -K: -f '\nKey (%K bytes): %k\t\nValue (%S bytes): %s\n\Partition: %p\tOffset: %o\n--\n' -t topic1

# Original script from https://hub.docker.com/r/confluentinc/cp-kafkacat
#docker run --tty \
#    --network docker-compose_default \
#    confluentinc/cp-kafkacat \
#    kafkacat -b kafka:29092 -C -K: \
#            -f '\nKey (%K bytes): %k\t\nValue (%S bytes): %s\n\Partition: %p\tOffset: %o\n--\n' \
#            -t foo