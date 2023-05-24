# l10n-xml-values-comparator
Tool for comparing strings  from 2 xml files by using levenshtein method and partial key values equality

## Usage
Control accurance by changing "similarity" value (the less value - more inaccurate results, but less values skipped)

There are three main executable methods.

### ExecuteConverter
This method is used to convert .string file into .xml

### ExecuteMerge
Used to merge all xml separate files from specific path into one .xml
This can be used, for example, if all strings in android/ios project are splitted into a lot of separated xmls (To combine all separated android xmls into one xml, or the same for ios)

### ExecuteComparator
Used to compare key values between xml1 and xml2.
This tool can be helpful to check l10n between projects even when keys are not the same between 2 xmls, but have partial equality in values (starting from 50%). 
If xml1 value1 equality is < 50% xml2 valu2 and etc - value will be skipped.

All results can be obtained within console.log file which is automatically generated in the project root

