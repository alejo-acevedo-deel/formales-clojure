(defn restar-48 [n] (- n 48))
(defn digs [n] (map restar-48 (map int(seq (str n)))))

(defn imprimir_frase [x] (format "Uno para %s, uno para mi" x))
(defn repartir
    ([] (imprimir_frase "vos")) 
    ([& n] (map imprimir_frase n))   
)

(defn juntarPare [x y]
    (concat
        (take-nth 2 (rest x))
        (take-nth 2 (rest y))
    )
)

(defn removel [l, num]
    (if (coll? l) (searchAndDestroy l num))
)

(defn searchAndDestroy [l, num]
    (map remove l num)
)