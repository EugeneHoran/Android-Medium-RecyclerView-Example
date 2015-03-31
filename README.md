# RecyclerView-Example
Simple example of a RecyclerView 

I didn't add the gradle so it is posted below.

	apply plugin: 'com.android.application'

	android {
		compileSdkVersion 21
		buildToolsVersion "21.1.2"

		defaultConfig {
			applicationId "com.eugene.recylerviewexample"
			minSdkVersion 14
			targetSdkVersion 21
			versionCode 1
			versionName "1.0"
		}
		buildTypes {
			release {
				minifyEnabled false
				proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
			}
		}
	}

	dependencies {
		compile fileTree(dir: 'libs', include: ['*.jar'])
		compile 'com.android.support:appcompat-v7:21.0.3'
		compile 'com.android.support:recyclerview-v7:+'
	}