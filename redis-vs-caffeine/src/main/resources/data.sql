INSERT INTO Posts (title, content)
SELECT
    CONCAT('Title ', seq.n) AS title,
    CONCAT('Content for post ', seq.n) AS content
FROM (
    SELECT @rownum := @rownum + 1 AS n
    FROM
        (SELECT 1 FROM information_schema.columns LIMIT 1000) a,
        (SELECT 1 FROM information_schema.columns LIMIT 1000) b,
        (SELECT @rownum := 0) r
    LIMIT 5000
) seq;
