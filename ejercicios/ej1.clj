;;;; Hacer una funcion que reciba los dos angulos y nos devuelva el tercero

(defn tercerAngulo [a b] (- 180 (+ a b)))

;;;; Defina una funcion que reciba dia hora minutos y segundo y devuelva el valor solo en segundos

(defn validations [d h m s] (and (>= s 0) (>= m 0) (>= h 0) (>= d 0)))
(defn makeSum [d h m s] (+ (* d 86400) (* h 3600) (* m 60) s))

(defn segundos [d h m s]
  (if (validations d h m s)
    (makeSum d h m s)
    (print (str "Tiempo negativo"))
  )
)

;;;; Escribir una funcion sig-mul-10 que reciba un numero entero y devuelva el primer multiplo de 10 que lo supere

(defn sig_mul_10 [a]
  (if (neg? a)
    (* 10 (- (quot a 10) 1))
    (* 10 (+ (quot a 10) 1))
  )
  )

;;;; Hacer una funcion que nos diga si el numero es capicua

(defn es_capicua [n]
  (= (reverse (str n)) (map char (str n)))
)

;;;; Funcion de fibonacci

(defn aux_fibo [i, h, f, s]
  (if (= i h) s (aux_fibo i (+ h 1) s (+ f s)))
)

(defn fibo [i]
  (if (= i 0)
    0
    (if (or (= i 1) (= i 2))
      1
      (aux_fibo i 2 1 1)
    )
  )
)
