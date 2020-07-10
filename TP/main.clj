(declare evaluar) (declare aplicar)
(declare controlar-aridad) (declare igual?)
(declare cargar-arch)
(declare imprimir)
(declare actualizar-amb)
(declare revisar-f)
(declare revisar-lae)
(declare buscar)
(declare evaluar-cond)
(declare evaluar-secuencia-en-cond)
; REPL (read–eval–print loop).
; Aridad 0: Muestra mensaje de bienvenida y se llama recursivamente con el ambiente inicial.
; Aridad 1: Muestra >>> y lee una expresion y la evalua
; Si la 2da. posicion del resultado es nil, retorna true (caso base de la recursividad).
; Si no, imprime la 1ra. pos. del resultado y se llama recursivamente con la 2da. pos. del resultado.
(defn repl ([]
    (println "Interprete de TLC-LISP en Clojure")
    (println "Trabajo Practico de 75.14/95.48 - Lenguajes Formales 2020")
    (println "Inspirado en:")
    (println " TLC-LISP Version 1.51 for the IBM Personal Computer")
    (println " Copyright (c) 1982, 1983, 1984, 1985 The Lisp Company") (flush)
    (repl '( add add append append cond cond cons cons de de env env equal equal eval eval exit exit
        first first ge ge gt gt if if lambda lambda length length list list load load lt lt nil nil not 
        not null null or or prin3 prin3 quote quote read read rest rest reverse reverse setq setq sub sub 
        t t terpri terpri + add - sub))
    )
    ([amb]
    (print ">>> ") (flush)
    (try (let [res (evaluar (read) amb nil)]
        (if (nil? (fnext res))
            true
            (do (imprimir (first res)) (repl (fnext res)))))
        (catch Exception e (println) (print "*error* ") (println (get (Throwable->map e) :cause)) (repl amb)))
    )
)

; Evalua una expresion usando los ambientes global y local. Siempre retorna una lista con un resultado y un ambiente.
; Si la evaluacion falla, el resultado es una lista con '*error* como primer elemento, por ejemplo: (list '*error* 'too-many-args) y el ambiente es el ambiente global. ; Si la expresion es un escalar numero o cadena, retorna la expresion y el ambiente global.
; Si la expresion es otro tipo de escalar, la busca (en los ambientes local y global) y retorna el valor y el ambiente global.
; Si la expresion es una secuencia nula, retorna nil y el ambiente global.
; Si el primer elemento de la expresion es '*error*, retorna la expresion y el ambiente global.
; Si el primer elemento de la expresion es una forma especial o una macro, valida los demas elementos y retorna el resultado y el (nuevo?) ambiente.
; Si no lo es, se trata de una funcion en posicion de operador (es una aplicacion de calculo lambda), por lo que se llama a la funcion aplicar,
; pasandole 4 argumentos: la evaluacion del primer elemento, una lista con las evaluaciones de los demas, el ambiente global y el ambiente local.
(defn evaluar [expre amb-global amb-local]
    
)


