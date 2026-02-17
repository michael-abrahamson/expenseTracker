run:
	cd expense-tracker && mvn -q exec:java

test:
	cd expense-tracker && mvn test

compile:
	cd expense-tracker && mvn compile