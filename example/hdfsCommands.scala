package example


class hdfsCommands{

  import org.apache.hadoop.conf.Configuration;
  import org.apache.hadoop.fs.FileSystem;
  import org.apache.hadoop.fs.Path;

  def copyFromLocal(): Unit = {
    val path = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/APIdata/"

    val src = "file:///home/maria_dev/output.csv"
    val target = path + "output.csv"
    println(s"Copying local file $src to $target ...")
    
    val conf = new Configuration()
    val fs = FileSystem.get(conf)

    val localpath = new Path(src)
    val hdfspath = new Path(target)
    
    fs.copyFromLocalFile(false, true, localpath, hdfspath)
    //fs.copyFromLocalFile(false, localpath, hdfspath)
    println(s"Done copying local file $src to $target ...")
  }//End of copyFromLocal()
} //End of Class