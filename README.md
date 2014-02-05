DMIselectionIndex
=================

Calculate selection index for expected Dry Matter Intake in Holstein based on production and fertility levels

*************************************
*   RUNNABLE INSTRUCTIONS of        *
*   DMIselectionIndex.jar           *
*   version Beta. February 5, 2014  *
*************************************

*Run as:*
java -jar DMIselectionIndex.jar input_file


where input file is a separated by commas ',' file containing in the following order: 
ID or name of the animal, ABV for milk, ABV for protein, ABV for fat, ABV for fertility, ABV reliability for production, ABV reliability for fertility

Example:
MANNA FARM DITTMAR,277,19,48,98,76,54
KANDES M14,998,34,50,105,63,40
KAARMONA CAPEFINN,1066,37,48,98,70,50
JERANG HEFFERNAN,311,21,38,104,76,66




**OUTPUT**

The (co)variances and economic values used are prompted in the terminal. Next versions will allow to specify these parameters.

The program prompts the index coefficients for milk, protein, fat and fertility ABV, respectively.
It also prompts the accuracy of the selection index.


The value of the selection index for each animal in the input file is written in the file values.txt with the following format (comma separated):
ID as in the input file,selection index value,reliability

Example:
MANNA FARM DITTMAR,0.18053550900100848,0.07800635894906009
KANDES M14,0.25655366899387466,0.06468720342900627
KAARMONA CAPEFINN,0.263790423844944,0.07184663415356604
JERANG HEFFERNAN,0.18666241164107805,0.07794580049114769
GLOMAR LONESTAR,0.21547431414242074,0.06980180345200031


