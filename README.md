# Over-engineering kata 2022
- Given the phrase: “DDD is basically structuring folders”
    - Return the same phrase converting all vowels (a,e,i,o,u) to the letter “i”. (The “y” is not considered as a vowel).
    - The second part will be to alternate all the letters between uppercase and lowercase.

- Non-functional requirements:
    - To be used only once, with a single string.
    - The response should be expected in less than 5 days.
    - The solution should not occupy more than 1 Petabyte.
    - Unlimited budget for infrastructure and manpower
    - Total freedom in terms of implementation technologies
    - Response expected in any type of media and format.

# Small diagram
![oekata-diagram](https://user-images.githubusercontent.com/4797703/195415079-883102b7-c6f5-4bc2-9322-995c62a0621e.png)

# How-to
Just execute main function to get the kata's result

# What is wrong with this:

- Using a full framework like spring boot for string 
transformations is just wrong
- Transformation class is nice because it gives type to
error flow... if it was needed, which is not
- Creating a service to do what one line of code
can do, don't
- Separating with functions the vowel transformation and
the alternation of letters, with an interface even! Come on!

# What could still be worse:
- Separating vowel transformation in a one-letter call (calls for each letter, because changing a letter is hard, right? right?)
- Making the vowel transformation using ssh on own machine
- Making the vowel transformation using in another machine
- Using coroutines to do the vowel transformation in other
machines and get the faster result. Because, you know, changing
strings is bitcoin computation level in difficulty (not)

# What could be better
- Unit tests in addition with feature tests
- Kotlin SSH library (didn't find one that worked) to make it 100% kotlin
- More functional concepts!

