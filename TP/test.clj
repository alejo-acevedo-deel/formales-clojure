(require '[clojure.test :refer [is deftest run-tests]])

(load-file "tlc-lisp.clj")

(deftest test-controlar-aridad
  (is (= '(*error* too-few-args) (controlar-aridad '(a b c) 4)))
  (is (= 4 (controlar-aridad '(a b c d) 4)))
  (is (= '(*error* too-many-args) (controlar-aridad '(a b c d e) 4)))
)

(deftest test-igual

  (is (igual? "mYFuN" "MYfun"))
  (is (igual? '() nil))
  (is (igual? nil '()))
  (is (igual? nil 'NIL))
  (is (igual? nil "NIL"))
  (is (igual? 5 5))
  (is (not (igual? 'a 'b)))
  (is (not (igual? 'a '())))
  (is (not (igual? nil '(1 2 3))))
)

(deftest test-imprimir
  (is (= (imprimir "hola") "hola"))
  (is (= (imprimir 5) 5))
  (is (= (imprimir 'a) 'a))
  (is (= (imprimir '(*error* hola "mundo")) '(*error* hola "mundo")))
  (is (= (imprimir '(hola "mundo")) '(hola "mundo")))
  (is (= (imprimir \space) \space))
)

(deftest test-actualizar-amb
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd 4) '(a 1 b 2 c 3 d 4)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'b 4) '(a 1 b 4 c 3)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd '(*error* test-error)) '(a 1 b 2 c 3)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd (list 1 2 3)) '(a 1 b 2 c 3 d (1 2 3))))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'b (list 1 2 3)) '(a 1 b (1 2 3) c 3)))
  (is (= (actualizar-amb '(+ add - sub) 'x 1) '(+ add - sub x 1)))
  (is (= (actualizar-amb '(+ add - sub x 1 y 2) 'x 3) '(+ add - sub x 3 y 2)))
)

(deftest test-revisar-f
  (is (= (revisar-f '(*error* test-error)) '(*error* test-error)))
  (is (nil? (revisar-f '(1 2 3 4))))
  (is (nil? (revisar-f 'doble)))
)

(deftest test-revisar-lae
  (is (= (revisar-lae '(1 2 3 (*error* test-error) 4 5 6)) '(*error* test-error)))
  (is (nil? (revisar-lae '(1 2 3 4))))
)

(deftest test-buscar
  (is (= (buscar '- '(+ add - sub)) 'sub))
  (is (= (buscar 'doble '(+ add - sub)) (list '*error* 'unbound-symbol 'doble)))
)

(deftest test-aplicar
  (is (= '((a b) (cons cons))(aplicar 'cons '(a (b)) '(cons cons) nil)))
  (is (= '(9 (+ add r 5)) (aplicar 'add '(4 5) '(+ add r 5) nil)))
  (is (= '(8 (+ add r 4 doble (lambda (x) (+ x x)))) (aplicar '(lambda (x) (+ x x)) '(4) '(+ add r 4 doble (lambda (x) (+ x x))) nil)))
)

(deftest test-evaluar
  (is (= '(3 (+ add r 3)) (evaluar '(setq r 3) '(+ add) nil)))
  (is (= '(doble (+ add doble (lambda (x) (+ x x)))) (evaluar '(de doble (x) (+ x x)) '(+ add) nil)))
  (is (= '(5 (+ add)) (evaluar '(+ 2 3) '(+ add) nil)))
  (is (= '((*error* unbound-symbol +) (add add)) (evaluar '(+ 2 3) '(add add) nil)))
  (is (= '(6 (+ add doble (lambda (x) (+ x x)))) (evaluar '(doble 3) '(+ add doble (lambda (x) (+ x x))) nil)))
  (is (= '(8 (+ add r 4 doble (lambda (x) (+ x x)))) (evaluar '(doble r) '(+ add r 4 doble (lambda (x) (+ x x))) nil)))
  (is (= '(6 (+ add)) (evaluar '((lambda (x) (+ x x)) 3) '(+ add) nil)))
  (is (= '(8 (+ add r 4 doble (lambda (x) (+ x x))))  (evaluar '(doble r) '(+ add r 4 doble (lambda (x) (+ x x))) nil)))
)

(deftest test-evaluar-cond
  (is (= '(nil (equal equal setq setq)) (evaluar-cond nil '(equal equal setq setq) nil)))
  (is (= '(nil (equal equal first first)) (evaluar-cond '(((equal 'a 'b) (setq x 1))) '(equal equal first first) nil)))
  (is (= '(2 (equal equal setq setq y 2)) (evaluar-cond '(((equal 'a 'b) (setq x 1)) ((equal 'a 'a) (setq y 2))) '(equal equal setq setq) nil)))
  (is (= '(3 (equal equal setq setq y 2 z 3)) (evaluar-cond '(((equal 'a 'b) (setq x1)) ((equal 'a 'a) (setq y 2) (setq z 3))) '(equal equal setq setq y 2 z 3) nil)))
)

(deftest test-evaluar-sec-cond
  (is (= '(2 (setq setq y 2)) (evaluar-secuencia-en-cond '((setq y 2)) '(setq setq) nil)))
  (is (= '(3 (setq setq y 2 z 3)) (evaluar-secuencia-en-cond '((setq y 2) (setq z 3)) '(setq setq) nil)))
)

(run-tests)