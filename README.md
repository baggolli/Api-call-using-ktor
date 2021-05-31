# Api-call-using-ktor
This is a small app used for demonstrating ktor android just for learning purpose
ktor is an android lib used as an interface for calling API's in android applications

Below are the dependecies we need to add inside gradle( app level )

    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-gson:$ktor_version")
		
    
And include the version inside project level gradle as:


buildscript {
    ext.ktor_version = '1.5.4'
}


And simply we need to create the HTTPClient as follows:


val client = HttpClient(Android) {

        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
}
    
Thats it!
