(defn sig_mul_10 [a]
  (if (neg? a)
    (* 10 (- (quot a 10) 1))
    (* 10 (+ (quot a 10) 1))
 )
)
