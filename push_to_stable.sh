#!/bin/bash
    cd $HOME
    git config --global user.email "aurelien.lefebvre@viacesi.fr"
	git config --global user.name "Travis"
    git config --global push.default matching
       

   git branch
   


   git checkout stable
	
    git pull origin master