set session cte_max_recursion_depth = 1000000;

insert into boards (title, content, created_at)
with recursive cte (n) as (
    select 1
    union all
    select n + 1 from cte where n < 1000000
)
select
    concat('Title', lpad(n, 7, '0')) as title,
    concat('Content', lpad(n, 7, '0')) as content,
    timestamp(date_sub(now(), interval floor(rand() * 3650 + 1) day) + interval floor(rand() * 86400) second ) as created_at
from cte;