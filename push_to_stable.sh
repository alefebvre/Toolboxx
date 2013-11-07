#!/bin/bash




    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "Travis"
	 git config --global push.default matching

	  
	 
         git clone --quiet https://${GH_TOKEN}@github.com/alefebvre/Toolboxx.git repo > /dev/null || error_exit "Error cloning the repository";
 

    # go into repo anc copy data
	 cd repo
 		
    git checkout stable
    git pull origin master
<<<<<<< HEAD
	git commit -m "travis stable"
=======
	git add *
<<<<<<< HEAD
	git commit -m "LOOOOOL"
>>>>>>> 3fa3f4d5b980cd2ed72ac6b0510529be3f9d47f6
=======
	git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed to stable"
>>>>>>> f7e1af002789d6e26801de2395388e1531238da8
	git push