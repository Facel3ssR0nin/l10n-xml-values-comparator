# l10n-xml-values-comparator
Tool for comparing strings  from 2 xml files by using levenshtein method and partial key values equality

## Usage
There are three main executable methods.

### ExecuteConverter
This method is used to convert .string file into .xml

### ExecuteMerge
Used to merge all xml separate files from specific path into one .xml
This can be used, for example, if all strings in android/ios project are splitted into a lot of separated xmls

### Execute Comparator
Used to compare key values between xml1 and xml2.
This tool can be helpful to check l10n between projects even when keys are not the same between 2 xmls, but have partial equality in values (starting from 50%). 

