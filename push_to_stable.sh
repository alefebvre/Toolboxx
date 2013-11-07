#!/bin/bash




    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "Travis"
	 git config --global push.default matching

	  
	 
         git clone --quiet https://${GH_TOKEN}@github.com/alefebvre/Toolboxx.git repo > /dev/null || error_exit "Error cloning the repository";
 

    # go into repo anc copy data
	 cd repo
 		
    git checkout stable
	
    git pull origin master
	
	git add *
<<<<<<< HEAD
	git commit -m "Travis  $TRAVIS_BUILD_NUMBER pushed to stable "
=======
	
	git commit -m "Travis $TRAVIS_BUILD_NUMBER pushed to stable"
	
>>>>>>> 72d5a8fca6a4002058e3043485c34d07f08038e9
	git push