# Útek z žaláře
## Mapa
```
                      G
                +-3--  -+
                |     ^ |
                |--~~~--|
                |       |
        +-1-----+-2-  --+-4-----+
    +p1-|  @&&  |       |       +p2-+
    |k1U|    S          D       | k2|
    +---|  V    |       |       +---+
+-------+-6-----+-5-  --+-7-----+        +-11----+
|       |       |       |       +p3-+    |       +p4-+
|   Č           D          d1   |   |    |  K    |Wk3|
|       |       |       |    C  +---+    |       +---+
+-------+-9-----+-8-  --+-10----+--------+---  --+
        |       |       |    R                   |
        |   P   D                                B  G
        |       |       |    R                   |
        +-------+-------+--------+-13  --+-12  --+
                             +p5-+       | R    R|
                             |   |   d2  |       |
                             +---+       |       |
                                 +-------+-------+
```

## Legenda
- S ... Startovní pozice
- & ... Řetěz
- @ ... Zámek
- V ... Žalářníkovo tělo
- k ... Klíč
  1. .. k celám
  2. .. k řetězu
  3. .. k bráně
- d? .. Diář  // pouze hinty
- U ... Žalářníkovo blečení
- ~ ... Nestabilní stěna
- D ... Dveře
- C ... Sýr
- P ... Palice
- G ... Východ
- ^ ... Železný sloupek
- Č ... Šaškova čepice
- S ... Stráž
- K ... Král
- B ... Brána
- W ... Koruna


## Místnosti
  1. Hráčova cela
    p1. žalářníkovo tělo
  2. Severní koridor žaláře
  3. Oubliette
  4. Prázdná cela
    p2. myší nora
  5. Střední koridor žaláře
  6. Cela
  7. Žalářníkova stanice
    p3. žalářníkův diář
  8. Jížní koridor žaláře
  9. Cela
 10. Koridor Hradu
 11. Koruní sál
    p4. královo tělo
 12. Stanice hradní stráže
 13. Kráova komnata
    p5. králův diář


## Příkazy
- jdi
- konec
- pomoc
- nápověda
- seber
- polož
- prozkoumej <Věc v prostoru> //Vytvoří pseudo místnost která umožní nové interakce
- použíj <Věc v batohu> na <Věc v prostoru>
- řekni <String>
- udeř
- nasaď si <Věc>
- sundej si <Věc>

## Extra
Na počátku hry si připoutaný řetězem ke stěně ve své cele a nemůžeš se od ní vzdálit na více jak 3 místnosti

## gameplay
 1. Search V
 2. Pick up k1
 4. Go to 1
 5. Go to 2
 6. Go to 5
 7. Use k1 on D7
 8. Go 7
 9. Pick up C
10. Go to 5
11. Go to 2
12. Use k1 on D4
13. Go to 4
14. Drop C
15. Go to 2
16. Go to 4
17. Pick up k2
18. Go to 2
19. Go to 1
20. Use k2 on @
21. Pick up &
22. Go to 2
23. Go to 5
24. Go to 8
25. Use k1 on D9
26. Go to 9
27. Pick up P
28. Go to 8
29. Go to 5
30. Go to 2
31. Go to 3
32. Equip P
33. Hit ~
34. Use & on ^
35. Go to G





