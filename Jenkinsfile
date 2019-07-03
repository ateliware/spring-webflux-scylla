node('master') {
    git credentialsId: '9822c2ba-21d1-40ab-b1fd-bee937e42879', url: 'ssh://git@github.com/ateliware/spring-webflux-scylla.git'
    gradlew('test')
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}