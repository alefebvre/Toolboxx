#!/bin/bash
    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "alefebvre"
	 git config --global push.default matching

	 
	 # using token clone gh-pages branch
        git clone --quiet https://${GH_TOKEN}@github.com/alefebvre/Toolboxx.git  > /dev/null || error_exit "Error cloning the repository";


	 
	 
   git checkout stable
   
   git pull origin master

