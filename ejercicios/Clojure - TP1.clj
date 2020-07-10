(defn new_row_sup [row i]
    (concat (repeat i 0) (nthrest row i))
)

(defn triangular_sup [matrix]
    (map new_row_sup matrix (iterate inc 0))
)

(defn triangular_sup_recu 
    ([matrix] 
        (if (= (next matrix) nil)
            (list (new_row_sup (first matrix) 0))
            (conj (triangular_sup_recu (next matrix) 1) (new_row_sup (first matrix) 0))
        )
    )
    ([matrix i]
        (if (= (next matrix) nil)
            (list (new_row_sup (first matrix) i))
            (conj (triangular_sup_recu (next matrix) (+ i 1)) (new_row_sup (first matrix) i))
        )
    )
)

(defn new_row_diag [row i]
    (concat (repeat i 0) (list (nth row i)) (repeat (- (count row) i 1) 0))
)

(defn diagonal [matrix]
    (map new_row_diag matrix (iterate inc 0))
)

(defn diagonal_recu 
    ([matrix] 
        (if (= (next matrix) nil)
            (list (new_row_diag (first matrix) 0))
            (conj (diagonal_recu (next matrix) 1) (new_row_diag (first matrix) 0))
        )
    )
    ([matrix i]
        (if (= (next matrix) nil)
            (list (new_row_diag (first matrix) i))
            (conj (diagonal_recu (next matrix) (+ i 1)) (new_row_diag (first matrix) i))
        )
    )
)