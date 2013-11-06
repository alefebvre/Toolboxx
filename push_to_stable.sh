#!/bin/bash
    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "alefebvre"

       

   git branch
   
   git clone --quiet https://${GH_TOKEN}@github.com/alefebvre/Toolboxx.git  > /dev/null || error_exit "Error cloning the repository";
     # go into repo anc copy data
   
		
   git checkout stable
	git add .
	git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed to stable"
    git pull origin master