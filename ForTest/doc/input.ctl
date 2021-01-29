OPTIONS(skip_index_maintenance=TRUE,direct=true,BINDSIZE=20971520,READSIZE=20971520,ERRORS=-1,ROWS=10000)
load data
CHARACTERSET AL32UTF8
append into table xxtj_etl_data_temp
fields terminated by '|'
trailing nullcols
(Org_id,Child_rep_id,Cell_name,Cell_value)