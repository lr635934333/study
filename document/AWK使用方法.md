# AWK使用方法

#### AWK工作流
**AWK遵循了非常简单的工作流 - 读取，执行和重复**
* Read
AWK从输入流（文件，管道或者标准输入）中读取一行，然后存储到内存中。
* Execute
所有的AWK命令都依次在输入上执行。默认情况下，**AWK会对每一行执行命令**，我们可以通过提供模式限制这种行为。
* Repeate
处理过程不断重复，直到到达文件结尾。

####  命令格式

``` shell
awk [options] 'pattern {action}' [file]
```
#### 示例
``` shell
# 列表中文件
ls -l *.md
-rw-r--r--. 1 root root 3213 May 20 19:31 CODE_OF_CONDUCT.md
-rw-r--r--. 1 root root  134 May 20 19:31 README.md
-rw-r--r--. 1 root root 5479 May 20 19:31 README.ru-RU.md
-rw-r--r--. 1 root root 4753 May 20 19:31 README.zh-CN.md

# awk 处理文件
ls -l | awk '/^-.*md$/ {if($5 > 4000)  print $0}'
-rw-r--r--. 1 root root  5479 May 20 19:31 README.ru-RU.md
-rw-r--r--. 1 root root  4753 May 20 19:31 README.zh-CN.md

# if判断
ls -l | awk '/^-.*md$/ {if($5 < 5000 && $5 > 4000)  print $3,$9}'
root README.zh-CN.md

# BEGIN、END语句；字符串拼接
ls -l | awk 'BEGIN{print "Begin\thello Awk"} /^-.*md$/ {if($5 < 5000 && $5 > 4000)  print "Body\t" $3 "-->" $9} END{print "End\ttotal:",NR}'
Begin   hello Awk
Body    root-->README.zh-CN.md
End     total: 37
```

#### Options
* -f progfile 指定一个文件作为awk脚本
* -F field-separator 指定分隔字符
* --profile 输出一份格式化后的程序到文件

#### Awk program
``` shell
# --profile 输出格式化后的awk程序代码
ls -l | awk --profile 'BEGIN{print "Begin\thello Awk"} /^-.*md$/ {if($5 < 5000 && $5 > 4000)  print "Body\t" $3 "-->" $9} END{print "End\ttotal:",NR}'
# gawk profile, created Tue May 21 12:20:20 2019
# BEGIN block(s)
BEGIN {
	print "Begin\thello Awk"
}
# Rule(s)
/^-.*md$/ {
    if ($5 < 5000 && $5 > 4000) {
    	print "Body\t" $3 "-->" $9
    }
}
# END block(s)
END {
	print "End\ttotal:", NR
}

```
