# boot<br />
test push<br />
<br />
<br />
Ubuntu完美安装搭建Git服务器<br />
<br />
<br />
最近公司项目需要用到Git来管理项目，正好逢周末花了点时间在虚拟机的Ubuntu系统中搭建了下git的服务器，由于搭建过程中多多少少遇到了一些小问题，再因为个人记性不太好，所以在这里记录下来，以备不时之需。开始我已经在虚拟机的Ubuntu 12.04系统下已经装过一次，忘了记录，这次重新再在虚拟机的Ubuntu 13.10系统下一步一步的来搭建，后面介绍包括了在客户端机器（宿主机器Win7）安装git客户端，及客户端git bash的简单使用。<br />
一、安装git服务器所需软件<br />
打开终端输入以下命令：<br />
ubuntu:~$ sudo apt-get install git-core openssh-server openssh-client<br />
git-core是git版本控制核心软件<br />
安装openssh-server和openssh-client是由于git需要通过ssh协议来在服务器与客户端之间传输文件
然后中间有个确认操作，输入Y后等待系统自动从镜像服务器中下载软件安装，安装完后会回到用户当前目录。如果
安装提示失败，可能是因为系统软件库的索引文件太旧了，先更新一下就可以了，更新命令如下：<br />
ubuntu:~$ sudo apt-get update <br />
更新完软件库索引后继续执行上面的安装命令即可。<br />
安装python的setuptools和gitosis，由于gitosis的安装需要依赖于python的一些工具，所以我们需要先安装python
的setuptools。<br />
执行下面的命令：<br />
ubuntu:~$ sudo apt-get install python-setuptools<br />
这个工具比较小，安装也比较快，接下来准备安装gitosis，安装gitosis之前需要初始化一下服务器的git用户信息,这个随便填。<br />
ubuntu:~$ git config --global user.name "myname"<br />
ubuntu:~$ git config --global user.email "******@gmail.com"<br />
初始化服务器用户信息后，就可以安装gitosis了，gitosis主要是用于给用户授权，设置权限也算是很方便的。<br />
可以通过以下命令获取gitosis版本文件<br />
ubuntu:~$ git clone https://github.com/res0nat0r/gitosis.git<br />
注意：中间有两个是数字零<br />
获取gitosis文件后，进入到文件目录下面<br />
ubuntu:/tmp$ cd gitosis/<br />
接着使用python命令安装目录下的setup.py的python脚本进行安装<br />
ubuntu:/tmp/gitosis$ sudo python setup.py install<br />
到这里，整个安装步骤就完成了，下面就开始对git进行一些基本的配置。<br />
二、创建git管理员账户、配置git<br />
创建一个账户（git）作为git服务器的管理员，可以管理其他用户的项目权限。<br />
ubuntu:/tmp/gitosis$ sudo useradd -m git<br />
ubuntu:/tmp/gitosis$ sudo passwd git<br />
然后再/home目录下创建一个项目仓库存储点，并设置只有git用户拥有所有权限，其他用户没有任何权限。<br />
ubuntu:/tmp/gitosis$ sudo mkdir /home/gitrepository<br />
ubuntu:/tmp/gitosis$ sudo chown git:git /home/gitrepository/<br />
ubuntu:/tmp/gitosis$ sudo chmod 700 /home/gitrepository/<br />
由于gitosis默认状态下会将仓库放在用户的repositories目录下，例如git用户的仓库地址默认在/home/git/repositories/目录下，这里我们需要创建一个链接映射。让他指向我们前面创建的专门用于存放项目的仓库目录/home/gitrepository。<br />
ubuntu:/tmp/gitosis$ sudo ln -s /home/gitrepository /home/git/repositories<br />
这里我将在服务器端生成ssh公钥，如果想在其他机器上管理也可以在其他机器上生成一个ssh的公钥。<br />
ubuntu:/home/git$ ssh-keygen -t rsa<br />
这里会提示输入密码，我们不输入直接回车即可。<br />
然后用刚生成公钥id_rsa.pub来对gitosis进行初始化。<br />
 <br />
出现如上信息说明gitosis已经初始化成功。<br />
gitosis主要是通过gitosis-admin.git仓库来管理一些配置文件的，如用户权限的管理。这里我们需要对其中的一个post-update文件添加可执行的权限。<br />
ubuntu:/home/git$ sudo chmod 755 /home/gitrepository/gitosis-admin.git/hooks/post-update<br />
三、服务器上创建项目仓库<br />
使用git账户在服务器上创建一个目录（mytestproject.git）并初始化成git项目仓库。<br />
ubuntu:/home/git$ su git<br />
$ cd /home/gitrepository<br />
$ mkdir mytestproject.git<br />
$ git init --bare<br />
$ exit<br />
如果出现以下信息就说明已经成功创建了一个名为mytestproject.git的项目仓库了，新建的这个仓库暂时还是空的，不能被客户端clone，还需要对gitosis进行一些配置操作。<br />
四、使用gitosis管理用户操作项目的权限<br />
首先需要在前面生成ssh公钥（用来初始化gitosis）的机器上将gitosis-admin.git的仓库clone下来。<br />
在客户端机器上新建一个目录用于存放gitosis-admin.git仓库<br />
ubuntu:~$ mkdir gitadmin<br />
ubuntu:~$ cd gitadmin/<br />
ubuntu:~/gitadmin$ git clone git@192.168.1.106:gitosis-admin.git<br />
clone正确会显示以下信息<br />
 <br />
clone下来会有一个gitosis.conf的配置文件和一个keydir的目录。gitosis.conf用于配置用户的权限信息，keydir主要用户存放ssh公钥文件（一般以“用户名.pub”命名，gitosis.conf配置文件中需使用相同用户名），用于认证请求的客户端机器。<br />
现在让需要授权的用户使用前面的方式各自在其自己的机器上生成相应的ssh公钥文件，管理员把他们分别按用户名命名好，复制到keydir目录下。<br />
ubuntu:~$ cp /home/aaaaa/Desktop/zhangsan.pub /home/aaaaa/gitadmin/gitosis-admin/keydir/<br />
ubuntu:~$ cp /home/aaaaa/Desktop/lisi.pub /home/aaaaa/gitadmin/gitosis-admin/keydir/<br />
继续编辑gitosis.conf文件<br />
[gitosis]<br />
[group gitosis-admin] <br />
####管理员组<br />
members = charn@ubuntu <br />
####管理员用户名，需要在keydir目录下找到相应的.pub文件，多个可用空格隔开（下同）<br />
writable = gitosis-admin####可写的项目仓库名，多个可用空格隔开（下同）<br />
[group testwrite] <br />
####可写权限组<br />
members = zhangsan####组用户<br />
writable = mytestproject####可写的项目仓库名<br />
[group<br />
 testread] ####只读权限组<br />
members =lisi####组用户<br />
readonly= mytestproject####只读项目仓库名<br />
因为这些配置的修改只是在本地修改的，还需要推送到服务器中才能生效。<br />
ubuntu:~/gitadmin/gitosis-admin$ git add .<br />
ubuntu:~/gitadmin/gitosis-admin$ git commit -am "add a user permission"<br />
ubuntu:~/gitadmin/gitosis-admin$ git push origin master<br />
推送成功会显示下面提示信息<br />
 <br />
又是后新增的用户不能立即生效，这时候需要重新启动一下sshd服务<br />
ubuntu:~/gitadmin/gitosis-admin$ sudo /etc/init.d/ssh restart<br />
现在，服务端的git就已经安装和配置完成了，接下来就需要有权限的组成员在各自的机器上clone服务器上的相应<br />
项目仓库进行相应的工作了。<br />
五、客户端（windows）使用git<br /><br />
下载安装windows版本的git客户端软件，下载地址：http://msysgit.github.io/<br />
安装完成后右键菜单会出现几个git相关的菜单选项，我们主要使用其中的git<br />
 bash通过命令行来进行操作。<br />
在本地新建一个目录，使用git初始化这个目录，然后再里面新建一个文本文件用于测试，最后关联到git服务器仓库<br />
中的相关项目，最后上传本地版本到服务器。<br />
$ mkdir testgit<br />
$ cd testgit<br />
$ git init<br />
$ echo "this is a test text file,will push to server" > hello.txt<br />
$ git add .<br />
$ git commit -am "init a base version,add a first file for push to server"<br />
$ git remote add origin git@serverip:mytestproject.git<br />
$ git push origin master<br />
这样服务端就创建好了一个mytestproject.git的仓库的基础版本了，现在其他组员只要从服务端进行clone就可以了。<br />
window下面进入到需要克隆的本地目录下面右键选择git bash选项，输入<br />
$ git clone git@serverip:mytestproject.git<br />
就可以把项目clone到本地仓库了。<br />
下面进行简单的修改和提交操作<br />
$ cd mytestproject<br />
$ echo "this is another text file created by other" >another.txt<br />
$ git add .<br />
$ git commit -am "add a another file by other"<br />
$ git push origin master<br />
最后推送到服务器成功会显示如下信息<br />
 <br />
GitHub 教程系列文章： <br />
GitHub 使用教程图文详解  http://www.linuxidc.com/Linux/2014-09/106230.htm <br />
Git 标签管理详解 http://www.linuxidc.com/Linux/2014-09/106231.htm <br />
Git 分支管理详解 http://www.linuxidc.com/Linux/2014-09/106232.htm <br />
Git 远程仓库详解 http://www.linuxidc.com/Linux/2014-09/106233.htm <br />
Git 本地仓库（Repository）详解 http://www.linuxidc.com/Linux/2014-09/106234.htm <br />
Git 服务器搭建与客户端安装  http://www.linuxidc.com/Linux/2014-05/101830.htm <br />
Git 概述 http://www.linuxidc.com/Linux/2014-05/101829.htm <br />
分享实用的GitHub 使用教程 http://www.linuxidc.com/Linux/2014-04/100556.htm <br />
Git 的详细介绍：请点这里<br />
Git 的下载地址：请点这里<br />
更多Ubuntu相关信息见Ubuntu 专题页面 http://www.linuxidc.com/topicnews.aspx?tid=2<br />
本文永久更新链接地址：http://www.linuxidc.com/Linux/2015-07/120616.htm<br />
<br />
<br />
