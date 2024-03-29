Step 1 :

Add it in your settings.gradle.kts file or if you don't have 'settings.gradle.kts' then add it into your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
			repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
			repositories {
				mavenCentral()
				maven { url 'https://jitpack.io' }
			}
		}

Step 2 :

1) Add below dependancy into your app level gradle file 

		dependencies {
					        implementation 'com.github.chiragpatel101:SingleChoiceBottomSheet:Tag'
			}

3) Note : here 'Tag' will be the latest version of the dependancy, suppose right now latest version is 1.0.7 in that case dependency will be like below

		dependencies {
					        implementation 'com.github.chiragpatel101:SingleChoiceBottomSheet:1.0.7'

			}

