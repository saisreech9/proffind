USE [master]
GO
/****** Object:  Database [proffind]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE DATABASE [proffind]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'proffind', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MYSQLSERVER\MSSQL\DATA\proffind.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'proffind_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MYSQLSERVER\MSSQL\DATA\proffind_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [proffind] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [proffind].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [proffind] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [proffind] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [proffind] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [proffind] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [proffind] SET ARITHABORT OFF 
GO
ALTER DATABASE [proffind] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [proffind] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [proffind] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [proffind] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [proffind] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [proffind] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [proffind] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [proffind] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [proffind] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [proffind] SET  DISABLE_BROKER 
GO
ALTER DATABASE [proffind] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [proffind] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [proffind] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [proffind] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [proffind] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [proffind] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [proffind] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [proffind] SET RECOVERY FULL 
GO
ALTER DATABASE [proffind] SET  MULTI_USER 
GO
ALTER DATABASE [proffind] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [proffind] SET DB_CHAINING OFF 
GO
ALTER DATABASE [proffind] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [proffind] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [proffind] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [proffind] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'proffind', N'ON'
GO
ALTER DATABASE [proffind] SET QUERY_STORE = OFF
GO
USE [proffind]
GO
/****** Object:  User [Udom]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [Udom] FOR LOGIN [Udom] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [sreee]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [sreee] FOR LOGIN [sreee] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [sree]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [sree] FOR LOGIN [sree] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [shanthan]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [shanthan] FOR LOGIN [shanthan] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [sathvik]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [sathvik] FOR LOGIN [sathvik] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [prabhath]    Script Date: 10/17/2022 5:32:28 PM ******/
CREATE USER [prabhath] FOR LOGIN [prabhath] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[ProfessorAvailability]    Script Date: 10/17/2022 5:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProfessorAvailability](
	[availableId] [int] NOT NULL,
	[profId] [int] NULL,
	[availableDay] [varchar](10) NULL,
	[timeId] [int] NULL,
	[isScheduled] [varchar](1) NULL,
PRIMARY KEY CLUSTERED 
(
	[availableId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProfessorDetails]    Script Date: 10/17/2022 5:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProfessorDetails](
	[profId] [int] NOT NULL,
	[profName] [varchar](60) NULL,
PRIMARY KEY CLUSTERED 
(
	[profId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 10/17/2022 5:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedule](
	[userId] [int] NULL,
	[availableId] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeSlots]    Script Date: 10/17/2022 5:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeSlots](
	[timeId] [int] NOT NULL,
	[availableTimeSlot] [varchar](60) NULL,
PRIMARY KEY CLUSTERED 
(
	[timeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserDetails]    Script Date: 10/17/2022 5:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserDetails](
	[userId] [int] NOT NULL,
	[userName] [varchar](60) NULL,
	[firstName] [varchar](60) NULL,
	[lastName] [varchar](60) NULL,
	[emailAddress] [varchar](60) NULL,
	[password] [varchar](60) NULL,
	[userType] [varchar](15) NULL,
	[createdTime] [varchar](20) NULL,
	[createdDate] [date] NULL,
	[modifiedTime] [varchar](20) NULL,
	[modifiedDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ProfessorAvailability]  WITH CHECK ADD FOREIGN KEY([profId])
REFERENCES [dbo].[ProfessorDetails] ([profId])
GO
ALTER TABLE [dbo].[ProfessorAvailability]  WITH CHECK ADD FOREIGN KEY([timeId])
REFERENCES [dbo].[TimeSlots] ([timeId])
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD FOREIGN KEY([availableId])
REFERENCES [dbo].[ProfessorAvailability] ([availableId])
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[UserDetails] ([userId])
GO
USE [master]
GO
ALTER DATABASE [proffind] SET  READ_WRITE 
GO
