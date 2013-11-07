#!/bin/bash
    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "alefebvre"
	 git config --global push.default matching

   git checkout -b stable
   git pull origin master
   git status
        git add .
        git commit -m "Travis Stable"
        git push 
        echo -e "Pushed to GitHub"
	
	 