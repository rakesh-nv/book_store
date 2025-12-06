$env:SPRING_DATA_MONGODB_URI='mongodb://rakeshn2204_db_user:cambridge2024@ac-vnnoves-shard-00-00.qqk9wo1.mongodb.net:27017,ac-vnnoves-shard-00-01.qqk9wo1.mongodb.net:27017,ac-vnnoves-shard-00-02.qqk9wo1.mongodb.net:27017/?authSource=admin&ssl=true&appName=Cluster0'
$env:MONGODB_URI='mongodb+srv://rakeshn2204_db_user:1cemwshyTjdTQl5y@cluster0.zumdlhj.mongodb.net/book-store?appName=Cluster0'
java -Dserver.port=8081 -jar target\book-store-0.0.1-SNAPSHOT.jar
