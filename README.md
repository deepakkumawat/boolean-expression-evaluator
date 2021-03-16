# Boolean Expression Evaluator

##Given a map of variable name as key and value as value along with a string of boolean expression.

###For example consider the following inputs:
###Input/Output
- Input1
  - Map of keys and values like a = 1, b = 6
  - Expression to be evaluated can be
    - (a < 4) && (b > 3)
    - ((a < 4) && (b > 3)) || ((a > 4) && !(b < 6))
- Input2
  - Map of keys and values like a = 1, b = 6, c = 10
  - Expression to be evaluated can be
    - (a < 4 && b > 3 && c < 9) || (a > 4 && !(b < 6) || c > 10)

- Output will be the result of expression, can be true or false

###Assumptions:
- Expression will not have any calculation in it, like + - / *
- Provided api should take 2 parameters as following
    - Map<String, Integer>
    - String
- The relational operator can be > <
- The conditional operators can be && || !
- Variable values can be integer only
