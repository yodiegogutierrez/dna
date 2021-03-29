# Magneto's DNA Checker
> MercadoLibre's coding exam
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
http://localhost:8080/mutant (local)
https://mutants-dna.herokuapp.com/mutant (remote)
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

Also you can get the stats on this URI
```sh
http://localhost:8080/stats (local)
https://mutants-dna.herokuapp.com/stats (remote)
```

The response should be something like this:
```sh
{
    "mutants": 7,
    "noMutants": 3,
    "averageMutants": 70.0
}
