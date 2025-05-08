# Útek z žaláře

Jednoduchá textová adventura, ve které se hráč ujímá role vězně uvězněného v hradním žaláři.

Hra probíhá v textovém prostředí, hráč zadává příkazy pomocí klávesnice a reaguje na popisy místností a událostí.

Úkolem hráče je uprchnout z vězení pomocí nalezených předmětů, interakcí s prostředím a postavami.

#### Autor: Jan Jurka

#### Verze: v1.0.0

---

### Poznámky:

- PMD rules set extrahovaný ze [stránek předmětu](java.vse.cz/wiki/uploads/Java/pmd-bluej-extension-2.1.1-bluej5.zip)
mi nešel použít kvůli chybové hlášce:
```sh
[ERROR] Cannot load ruleset rulesets/basic.xml/EmptyCatchBlock: Cannot resolve rule/ruleset reference 'rulesets/basic.xml/EmptyCatchBlock'.  Make sure the resource is a valid file or URL and is on the CLASSPATH. Use --debug (or a fine log level) to see the current classpath.
[WARN] Progressbar rendering conflicts with reporting to STDOUT. No progressbar will be shown. Try running with argument -r <file> to output the report to a file instead.
```
použil jsem tudíž defaultní ruleset pro pmd 7.13.0

- PMD hlásí problémy se switchem ve třídě Vec, nicméně fallthrough jednotlivých case statmentů je zde úmyslný
- Pořadí parametrů v testovacích assertech je vědomě prohozeno, aby odpovídalo dříve odevzdanému zadání
- vytvořeno za použití JDK ms-17.0.15
  - otestováno s JDK jbr-21 a openjdk-23.0.2
