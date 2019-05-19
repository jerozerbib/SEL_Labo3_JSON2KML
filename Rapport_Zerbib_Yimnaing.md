# SERIALISATION - LAB 03 :  JSON => KML 

*Auteurs : Jeremy ZERBIB, Créscence YIMNANG.*

*Date   : 14. Mai 2019*

#### INTRODUCTION

Il a  été question pour nous, pendant ce laboratoire, de parser le fichier `JSON` (`geojson`) qui nous a été fournit, puis de créer un fichier `KML` « Keyhole Markup Langage » sur la base du geojson, qui devra être testé sur l'application *Google Earth*.

#### Choix relatifs à la realisation

Afin de répondre au mieux aux exigences du cahier des charges, nous avons créé, pour faciliter le parsing du fichier geojson les classes `Properties`. Leurs implémentations sont simples et redéfinissent les propriétés `Properties(Admin, ISOA3)`. 

Le reste du traitement du fichier `geojson` se fera dans la classe application notamment l'écriture du fichier kml. 


#### Les principales difficultés rencontrées dans ce travail.

Le manque de ligne directive pour cet exercice, a constitué pour nous un léger frein, sinon rien de bien grave. 

Nous avons eu quelques petits soucis quant à l'écriture du document KML et le tracé des lignes lors des tests sur l'application Google Earth. Cela a pu être résolu en utilisant les balises `<Style>` et `<PolyStyle>`.

Dans l'ensemble, nous n'avons pas rencontré de difficultés majeures.  


##### Copie d’écran de l’affichage lors du parsing du fichier geojson.

(Résultats des premières lignes). 

  ``` xml
(ABW) Aruba
	 - 26 coordinates
(AFG) Afghanistan
	 - 1533 coordinates
(AGO) Angola
	 - 14 coordinates
	 - 1492 coordinates
	 - 139 coordinates
(AIA) Anguilla
	 - 24 coordinates
	 - 4 coordinates
(ALB) Albania
	 - 557 coordinates
(ALA) Aland
	 - 12 coordinates
	 - 17 coordinates
	 - 15 coordinates
	 - 32 coordinates
	 - 9 coordinates
	 - 15 coordinates
	 - 24 coordinates
	 - 21 coordinates
	 - 14 coordinates
	 - 10 coordinates
	 - 40 coordinates
	 - 20 coordinates
	 - 13 coordinates
	 - 19 coordinates
	 - 200 coordinates
	 - 11 coordinates
(AND) Andorra
	 - 51 coordinates
(ARE) United Arab Emirates
	 - 53 coordinates
	 - 16 coordinates
	 - 19 coordinates
	 - 15 coordinates
	 - 17 coordinates
	 - 45 coordinates
	 - 18 coordinates
	 - 13 coordinates
	 - 7 coordinates
	 - 582 coordinates
	 - 20 coordinates
(ARG) Argentina
	 - 13 coordinates
	 - 148 coordinates
	 - 193 coordinates
	 - 22 coordinates
	 - 15 coordinates
	 - 25 coordinates
	 - 14 coordinates
	 - 4271 coordinates
.....
  ```


##### Copie d’écran après chargement de votre fichier KML sur le site internet GoogleEarth  


![Capture d'ecran](/home/jeremy/Desktop/Semestre4/SER/Labos/Labo3/screenshot.png)

##### Nos apprentissages 

 * Découverte du format kml.
 * Transformation geojson en kml. 
 * Ecriture d'un document kml.

#### Conclusion

La réalisation de cet exercice nous a permis de découvrir et d'apprendre de nouvelles choses. Nous avons eu de legéres difficultés avec l'écriture du fichier kml en l'absence d'une ligne directive, mais nous sommes tout de même parvenus à obtenir un résultat qui nous semble tout à fait satisfaisant et répondant aux critères imposés par le cahier des charges. 

## Signatures des élèves : 

Jeremy Zerbib

Créscence Ginéle Yimnaing Kamdem