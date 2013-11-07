#!/bin/bash


	# copy data we're interested in to other place
		cp -Rv Application $HOME/Application
        cp -Rv test $HOME/test

  # go to home and setup git
	cd $HOME
    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "Travis"
	 git config --global push.default matching

	  
	 
	 # using token clone gh-pages branch
        git clone --quiet https://${GH_TOKEN}@github.com/alefebvre/Toolboxx.git repo > /dev/null || error_exit "Error cloning the repository";

        # go into repo and copy data
        cd repo
	   # checkout stable
	   git checkout -b stable
	   
	     # copy stuff
	  cp -Rv $HOME/Application Application/
       cp -Rv $HOME/test test/
	 
   
       # Pull, add, commit and push files
   git pull origin master

		git add .
        git commit -m "Travis Stable"
        git push 