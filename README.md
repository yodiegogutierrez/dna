# Magneto's DNA Checker
> Initial project for any Spring Boot project
### What's in?
This project is created to help Magneto to verify if DNA maps for certain people corresponds to mutant combinations

### This project includes
- Lombok ( v. 1.18.12 )
- Jackson ( v. 2.11.0.rc1 )
- Spring Boot Web Dependency
- Spring Boog DevTools Dependency

### About the App

Run the service:
```sh
mvn spring-boot:run
```

Request a post to the URI
```sh
http://localhost:8080/mutant
```

Body's example
```sh
{
  "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
Responses
```sh
200 Ok - If DNA corresponds to a mutant
403 Forbidden - If DNA doesn't corresponds to a mutant
406 Not Acceptable - If the matrix is not nxn
```