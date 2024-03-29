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

4) Suppose you want display list of users into bottom sheet and data class of user will be like this
		
        data class UserEntity(val id:Int,val name:String,val email:String)

5) To open bottom sheet for the list of users

       private fun openBottomSheet() {
           val list = arrayListOf<UserEntity>()
             for (i in 0..50) {
                 val user = UserEntity((i+1),"Item ${i+1}","itemNo${i + 1}@gmail.com")
                 list.add(user)
             }

         val bottomSheet = SingleChoiceBottomSheetFragment<UserEntity>(dialogTitle = "User List"
            , itemList = list,
            displayFieldName = "email",
            preSelectedItem = selectedUser,allowSearchIntoList = true) { selectedItem ->
            Toast.makeText(this@MainActivity,selectedItem.name,Toast.LENGTH_SHORT).show()
            selectedUser = selectedItem
        }

        bottomSheet.show(supportFragmentManager, bottomSheet.tag)

   }

   Key parameter explanation :

       UserEntity -> Type of array list which you have to pass into bottom sheet
       dialogTitle -> title which you want to display for bottom sheet
       itemList -> list of item which you want to pass 
       displayFieldName -> name of parameter which you want to display for bottomsheet list, here in demo list of email will be display as the above code
       allowSearchIntoList -> if you don't want search functionality in the list then pass as a 'false', by default it's true and you will have search functionality
       preSelectedItem -> pass pre selected value if you want to display user already selected value 


    
