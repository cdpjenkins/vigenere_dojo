(ns vigenere-dojo.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def table-line (vec (map char (range (int \A) (inc (int \Z))))))

(defn rotate [line i]
  (concat (drop i line) (take i line)))

(def table-whole (vec (map (partial rotate table-line) (range 0 26))))

(defn char-to-int [c]
  (-  (int c) (int \A)))

(defn encode-pair [pt-char key-char]
  (nth (nth table-whole key-char) pt-char))

(defn encrypt [plain-text key-word]
  (let [pairs (map encode-pair
                   (map char-to-int plain-text) (map char-to-int (cycle key-word)))]

    (apply str pairs)))

(defn decode-pair [pt-char key-char]
  (let [row (nth table-whole key-char)
        i (.indexOf (map char-to-int row) pt-char)]
    (nth table-line i)
    ;[pt-char key-char row i]
    ))


(defn decrypt [ct k]
  (let [pairs (map decode-pair
                   (map char-to-int ct) (map char-to-int (cycle k)))]
                                        ;
    (apply str pairs)))


(def plain-text "ABCDEFG" )
(def key "LEMONLEM")




(map vector plain-text key)
