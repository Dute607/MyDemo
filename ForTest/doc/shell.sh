#!/bin/sh
###加载环境变量
. ~/.bash_profile
########数据连接串
conndb=scott/orcl@127.0.0.1:1521/orcl

#数据文件路径
##入库的数据文件夹
SOUR_DIR=/home/source
##入库后的目的地
DEST_DIR=/home/dest
##入库配置文件
CTRL_FILE=/home/input.ctl

for element in 'ls $SOUR_DIR'
do
	SOUR_FILE=$SOUR_DIR"/"$element
	sqlldr ${conndb} control=${CTRL_FILE} data=${SOUR_FILE}
	mv ${SOUR_FILE} ${DEST_DIR}
done
echo "入库完成"