Calculating your Overtime
=========================

This short Scala command-line application parses a text file where I record for each week how many work days there are
and how many hours I worked.

Sample input:
    5d	 39h50m	# CW51 17.12.-23.12. (two days of vacation)
    3d	 23h53m # CW52 24.12.-30.12.
    4d	 30h48m	# CW01 31.12.-6.1.2013 (half day of vacation)

Sample output:
      80 -     5d	 39h50m	# CW51 17.12.-23.12. (two days of vacation)
      47 -     3d	 23h53m # CW52 24.12.-30.12.
       0 -     4d	 30h48m	# CW01 31.12.-6.1.2013 (half day of vacation)
    ====
     127 = 2h7m = 2,12

The implementation uses Scala's parser combinators.

You can simply run it with
    sbt run filename
