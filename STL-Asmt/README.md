# Coding Test:

## section A:
Q1:
a. Can you implement the sing() method for the bird?
- No. There is no sing() method is implemented either in Animal or Bird class. So when the code compile will get an error (the sing() method is undefined for the Bird type).

b. How did you optimize the code for maintainability? 
- We know that not all of the subclasses should not have flying and singing behaviour so inheritance is not the correct solution when its come to maintenance. We could take out fly() and sing() methods from Bird class and make separate interfaces for fly() and sing(). That way, only the birds that are supposed to fly will implement that interface.

Q2: Now, we have 2 special kinds of birds: the Duck and the Chicken... Can you implement them to make their own special sound?
- Provided simulator classes and unit-test classes for the functionality testing.

Q3: Now how would you model a rooster?
- Provided simulator class and unit test class for test the functionality.

Q4:
d. How do you keep the parrot maintainable? What if we need another parrot lives near a Duck? Or near a phone that rings frequently?
- We can keep parrtot maintainable using Composition (Has-A relationship) with abstraction (implementing interfaces). Here the parrot has a relationship with cat, dog, duck and rooster.

## section B:
- Test and Simulator classes created to test the behaviour.

## section D: 
Q2: Can you optimize your model to account for the metamorphosis from caterpillar to butterfly?
- We can model caterpillar and butterfly using Composition/Aggregation - there is a relationship between caterpillar and butterfly since Caterpillar is transforming to butterfly.

## section E: 
Q1: Can you share the code to count:
a. how many of these animals can fly?
- Test class implemented.

b. how many of these animals can walk?
- Test class implemented.

c. how many of these animals can sing?
- Test class implemented.

d. how many of these animals can swim?
- Test class implemented.
