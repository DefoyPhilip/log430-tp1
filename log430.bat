@echo off
::
:: Script pour "epurer" les variables d'environnement PATH et CLASSPATH
:: et demarrer une coquille DOS utilisable pour executer les exemples
:: fournis avec le cours et realiser les laboratoires du cours.
::
:: Destine aux etudiants de l'ETS. Conseil: copiez ce script a un endroit ou
:: vous avez le plein controle et creez un raccourci vers celui-ci sur votre
:: "bureau" Windows, ce sera utile durant les laboratoires tout au long de la
:: session.
::
:: Tous les repertoires utilises dans ce script refletent l'installation des
:: logiciels dans les laboratoires du Departement.
::
:: Le script est divise en sections car utilise dans plusieurs cours. Les
:: sections non pertinentes pour un cours donne peuvent etre retirees ou mises
:: en commentaire. Le script regle dans l'ordre les parametres lies aux
:: logiciels suivants:
::
:: 1 - le script lui-meme;
:: 2 - le SDK java;
:: 3 - une distribution de Apache Maven.
::
:: Enfin, le script demarre une coquille (shell) DOS dans un repertoire
:: specifique. Cette coquille peut alors etre utilisee pour compiler les projets
:: java (via javac ou Maven) lies au cours. 
::
:: Par Roger Champagne, P.Eng., Ph.D.
::     2008-Sep-08
:: MAJ 2015-Sep-04

:: 1 - Parametres globaux ======================================================

:: Racine du lecteur ou ce script est stocke. Pour utilisation dans les
:: laboratoires de l'ETS, il est suggere d'utiliser votre lecteur reseau (J:) ou
:: une barette de memoire USB (typiquement E:). Sur votre ordinateur personnel,
:: C: est la valeur typique.
set COURS=log430
set CONFIG_DRIVE=j:

:: Repertoire ou l'on souhaite stocker le present script
set CONFIG_HOME=%CONFIG_DRIVE%\%COURS%

:: Ecrasement du path, seul le repertoire ci-haut est conserve. Il est
:: fortement recommande d'ecraser le PATH par defaut sur les postes du lab
:: a l'ETS, car cette variable est surchargee et contient parfois des entrees
:: qui causent des problemes dans ce cours (conflits de versions, etc).
set path=%CONFIG_HOME%

:: 2 - Reglages SDK java =======================================================

:: Repertoire du SDK Java a utiliser - plusieurs versions installees, choississez
:: celle qui vous convient.
set JAVA_HOME=C:\Oracle\java\jdk8_45_x64

:: path: ajout des repertoires pertinents pour le SDK java.
set path=%PATH%;%JAVA_HOME%\bin

:: Ecrasement du classpath, seul le repertoire courant est conserve. Il est
:: fortement recommande d'ecraser le CLASSPATH par defaut sur les postes du lab
:: a l'ETS, car cette variable est surchargee et contient parfois des entrees
:: qui causent des problemes dans ce cours (conflits de versions, etc).
set classpath=.

:: 3 - Reglages Maven ==========================================================

:: Repertoire de la distribution maven a utiliser
set MVN_HOME=c:\apache\maven\3.2.2

:: path: ajout des repertoires pertinents pour Maven.
set path=%PATH%;%MVN_HOME%\bin;

:: 4 - Demarrage d'une coquille (shell) a un endroit predetermine ==============

:: Lecteur et repertoire d'ou l'on veut demarrer la coquille.
:: Modifier selon vos besoins.
set START_DRIVE=%CONFIG_DRIVE%
set START_DIR=\%COURS%

%START_DRIVE%
call C:\WINDOWS\system32\cmd.exe /k cd %START_DIR%