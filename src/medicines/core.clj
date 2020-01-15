(ns medicines.core)

(def sample-data
  {
   :warehouse  {
                :paracetamol 10
                :ibuprofen   50
                :syringe     80
                :scalpel     100
                }

   :icu        {
                :paracetamol 0
                :ibuprofen   20
                :syringe     7
                :scalpel     8
                }

   :dispensary {
                :paracetamol 50
                :ibuprofen   33
                :syringe     10
                :scalpel     80
                }
   })

(def transactions
  [{:from     :warehouse
    :to       :dispensary
    :item     :paracetamol
    :quantity 2}
   {:from     :warehouse
    :to       :dispensary
    :item     :ibuprofen
    :quantity 5}
   {:from     :dispensary
    :to       :icu
    :item     :syringe
    :quantity 2}
   {:from     :warehouse
    :to       :icu
    :item     :scalpel
    :quantity 10}])

(defn perform-transaction
  [data {:keys [from to item quantity]}]
  (-> data
      (update-in [to item] + quantity)
      (update-in [from item] - quantity)))


(defn perform-transactions
  [data transactions]
  (reduce perform-transaction data transactions))

(perform-transactions sample-data transactions)

