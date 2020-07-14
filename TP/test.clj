(require '[clojure.test :refer [is deftest run-tests]])

(load-file "tlc-lisp.clj")

(deftest test-controlar-aridad
  (is (= '(*error* too-few-args) (controlar-aridad '(a b c) 4)))
  (is (= 4 (controlar-aridad '(a b c d) 4)))
  (is (= '(*error* too-many-args) (controlar-aridad '(a b c d e) 4)))
)

(deftest test-igual
  (is (igual? "myFun" "MYfun"))
  (is (igual? '() nil))
  (is (igual? 5 5))
  (is (not (igual? 'a 'b)))
  (is (not (igual? 'a '())))
  (is (not (igual? nil '(1 2 3))))
)

(deftest test-imprimir
  (is (= (imprimir "Esto es una cadena de prueba") "Esto es una cadena de prueba"))
  (is (= (imprimir '(*error* test-error)) '(*error* test-error)))
  (is (= (imprimir '(esto es un test)) '(esto es un test)))
  (is (= (imprimir \space) \space))
)

(deftest test-actualizar-amb
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd 4) '(a 1 b 2 c 3 d 4)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'b 4) '(a 1 b 4 c 3)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd '(*error* test-error)) '(a 1 b 2 c 3)))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'd (list 1 2 3)) '(a 1 b 2 c 3 d (1 2 3))))
  (is (= (actualizar-amb '(a 1 b 2 c 3) 'b (list 1 2 3)) '(a 1 b (1 2 3) c 3)))
)

(deftest test-revisar-f
  (is (= (revisar-f '(*error* test-error)) '(*error* test-error)))
  (is (nil? (revisar-f '(1 2 3 4))))
)

(deftest test-revisar-f
  (is (= (revisar-lae '(1 2 3 (*error* test-error) 4 5 6)) '(*error* test-error)))
  (is (nil? (revisar-lae '(1 2 3 4))))
)

(deftest test-buscar
  (is (= (buscar 1 '(1 2 3 4 5 6)) 2))
  (is (= (buscar 6 '(1 2 3 4 5 6)) (list '*error* 'unbound-symbol 6)))
)

(deftest test-aplicar
  (is (= '((a b) (cons cons))(aplicar '(cons) '(a (b)) '(cons cons) nil)))
  (is (= '(9 (+ add r 5)) (aplicar 'add '(4 5) '(+ add r 5) nil)))
  (is (= '(8 (+ add r 4 doble (lambda (x) (+ x x)))) (aplicar '(lambda (x) (+ x x)) '(4) '(+ add r 4 doble (lambda (x) (+ x x))) nil)))
)

(run-tests)