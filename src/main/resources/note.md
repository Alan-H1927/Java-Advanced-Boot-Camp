提交git失败问题：

Failed to connect to github.com port 443: Timed out

解决方案：
git config --global --unset http.proxy
git config --global --unset https.proxy
