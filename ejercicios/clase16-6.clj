(defn ej1_aux [s]
    (list (apply str s))
)

(defn ej1 [org i] 
    (map
        ej1_aux
        (partition i org)
    )
)

(def matrix (list 
    (list \V \F \V)
    (list \F \V \V)
    (list \V \V \V)
    (list \F \F \F)
))

(defn cantV [row] (count (filter (fn [item] (= item \V)) row)))
(defn cantF [row] (count (filter (fn [item] (= item \F)) row)))

(defn get-value [row max]
    (if (= (cantV row) max)
        1
        0
    )
)

(defn get-max [matrix]
    (apply max (map cantV matrix))
)


(defn generar-mascara [matrix]
    (map get-value matrix repeat (count matrix) (get-max matrix))
)


(defn is-one [value index]
    (if (= index 1)
    value
    )
)

(defn aplicar-mascara [row mask]
    (remove nil? (map is-one row mask))
)

(defn filas-max-V [matrix] (aplicar-mascara (range 1 (inc (count matrix))) (generar-mascara matrix)))

(defn total_V [matrix]
    (reduce + (map cantV matrix))    
)

(defn total_F [matrix]
    (reduce + (map cantF matrix))    
)

(defn mas-v-o-f [matrix]
    (if (> (total_V matrix) (total_F matrix))
        \V
        \F
    )
)

(defn distl [ch lis] 
    (map (fn [a b] (list a b)) lis (repeat (count lis) ch))
)